<template>
  <div class="home">
    <h1>Welcome to Open-Data-Service UI</h1>

    <v-card class="mx-auto">
      <v-toolbar
        dense
        class="elevation-0"
      >
        <v-toolbar-title>
          <span>Location Decompose: Jens Working Area</span>
        </v-toolbar-title>
      </v-toolbar>
      <v-card-text>
        <location-decomposer v-model="decomposedUrl" />
      </v-card-text>
    </v-card>

    <v-card class="mx-auto mt-5">
      <v-toolbar
        dense
        class="elevation-0"
      >
        <v-toolbar-title>
          <span>Location Decompose: Returned to wrapping component</span>
        </v-toolbar-title>
      </v-toolbar>
      <v-card-text>
        {{ decomposedUrl }}
      </v-card-text>
    </v-card>
  </div>
</template>

<script lang="ts">
import Vue from 'vue'
import Component from 'vue-class-component'

import LocationDecomposer, { DecomposedUrl } from '../datasource/edit/adapter/LocationDecomposer.vue'

@Component({
  components: { LocationDecomposer }
})
export default class Home extends Vue {
  private decomposedUrl: DecomposedUrl = {
    url: 'http://www.example.org/top/suche?stichwort=wiki&ausgabe=liste#last',
    baseurl: '',
    rest: [],
    anchor: '',
    queryparameters: [],
    parameters: []
  }
}
</script>
