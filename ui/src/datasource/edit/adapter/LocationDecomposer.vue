<template>
  <div>
    <v-text-field
      v-model="decomposedUrl.url"
      label="URL"
      class="pl-7"
    />
    <v-list>
      <v-list-item
        v-for="query in decomposedUrl.parameters"
        :key="query.uid"
      >
        <query-parameter :value="query" />
      </v-list-item>
    </v-list>
    <v-btn
      color="primary"
      @click="send"
    >
      Send
    </v-btn>
  </div>
</template>

<script lang="ts">
import Vue from 'vue'
import Component from 'vue-class-component'

import { Emit, PropSync, Watch } from 'vue-property-decorator'
import QueryParameter, { Parameter } from '@/datasource/edit/adapter/QueryParameter.vue'

@Component({
  components: {
    QueryParameter
  }
})
export default class LocationDecomposer extends Vue {
  @PropSync('value') // passed from wrapping component via v-model
  private decomposedUrl!: DecomposedUrl;

  @Emit('value') // updates v-model in wrapping component
  emitValue (): DecomposedUrl {
    return this.decomposedUrl
  }

  updateParameter (parameter: Parameter): void{
    const index = this.decomposedUrl.parameters.findIndex(p => {
      return p.uid === parameter.uid
    })
    this.decomposedUrl.parameters[index] = parameter
  }

  @Watch('decomposedUrl.url')
  private formChanged (): void {
    // Build a parameter array.
    const regexp = new RegExp(
      '(?<baseurl>^[a-z]*:\\/\\/.*?)\\/(?<rest>.*?)(\\?|$)(?<query>.+?\\=.+?&|.+?\\=.+?)(?<anchor>#.*|$)'
    ) // Regex for destructing url
    const match = regexp.exec(this.decomposedUrl.url)
    if (match != null) {
      this.decomposedUrl.parameters = [] // only if there is a match a new parameters array is created
      if (match.groups != null) {
        let count = 0
        if (match.groups.baseurl != null) { // get the baseurl
          this.decomposedUrl.baseurl = match.groups.baseurl
        } else {
          this.decomposedUrl.baseurl = ''
        }
        if (match.groups.rest != null) { // get all rest parameters
          this.decomposedUrl.rest = match.groups.rest.split('/')
        }
        if (match.groups.query != null) { // get all query parameters
          const queryList = match.groups.query.split('&')
          let queryParameters: string[][]
          queryParameters = []
          for (let i = 0; i < queryList.length; i++) {
            queryParameters.push(queryList[i].split('='))
          }
          this.decomposedUrl.queryparameters = queryParameters
        }
        if (match.groups.anchor != null) { // get the anchor
          this.decomposedUrl.anchor = match.groups.anchor
        } else {
          this.decomposedUrl.anchor = ''
        }

        // create rest parameters
        for (let i = 0; i < this.decomposedUrl.rest.length; i++) {
          const key = this.decomposedUrl.rest[i]
          const parameter: Parameter = ({
            uid: count,
            type: 'rest',
            changeable: false,
            key: key,
            value: key,
            default: key
          })
          count++
          this.decomposedUrl.parameters.push(parameter)
        }
        // create query parameters
        for (let i = 0; i < this.decomposedUrl.queryparameters.length; i++) {
          const key = this.decomposedUrl.queryparameters[i][0]
          const value = this.decomposedUrl.queryparameters[i][1]
          const parameter: Parameter = {
            uid: count,
            type: 'query',
            changeable: false,
            key: key,
            value: value,
            default: key
          }
          count++
          this.decomposedUrl.parameters.push(parameter)
        }
        // sort them in created order
        // if order should be changeable there has to be adjustments
        this.decomposedUrl.parameters.sort((a, b) => a.uid - b.uid)
      }
    }
  }

  // dummy function for sending the data to backend
  // TODO: POST to backend
  send (): void {
    console.log({
      baseurl: this.decomposedUrl.baseurl,
      parameters: this.decomposedUrl.parameters
    })
  }
}

export interface DecomposedUrl {
  url: string;
  baseurl: string;
  rest: string[];
  anchor: string;
  queryparameters: string[][];
  parameters: Array<Parameter>;
}

</script>
