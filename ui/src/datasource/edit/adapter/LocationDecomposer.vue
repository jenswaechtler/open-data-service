<template>
  <v-text-field
    v-model="url"
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
  private url = "http://test.de/abc";

  formChanged() {

  }

}
</script>
