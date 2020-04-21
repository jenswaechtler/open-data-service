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
        <location-decomposer />
      </v-card-text>
    </v-card>
  </div>

</template>

<script lang="ts">
import Vue from 'vue'
import Component from 'vue-class-component'

import LocationDecomposer from '../datasource/edit/adapter/LocationDecomposer.vue'

@Component({
  components: { LocationDecomposer }
})
export default class Home extends Vue {
}
</script>
