<template>
  <v-text-field
    v-model="decomposedUrl.url"
    label="URL"
    class="pl-7"
    @keyup="formChanged"
  />
</template>

<script lang="ts">
import Vue from 'vue'
import Component from 'vue-class-component'

import {Emit, Prop, PropSync, Watch} from 'vue-property-decorator'

@Component({ })
export default class LocationDecomposer extends Vue {

  @PropSync('value') // passed from wrapping component via v-model
  private decomposedUrl!: DecomposedUrl;

  @Emit('value') // updates v-model in wrapping component
  emitValue () {
    return this.decomposedUrl
  }

  formChanged() {
    // TODO: do something after intput changed
  }
}

export interface DecomposedUrl {
  url: string,
  parameters: {
    key: string,
    default: string
  }[]
}

</script>
