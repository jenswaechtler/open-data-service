<template>
  <v-list-item-content>
    <v-container>
      <v-row
        dense
        class="align-center"
      >
        <v-col
          cols="1"
        >
          <v-card
            class="pa-1"
            color="grey"
          >
            {{ parameter.type.toLocaleUpperCase() }}
          </v-card>
        </v-col>
        <v-col
          cols="2"
        >
          <v-checkbox
            v-model="parameter.changeable"
            label="changeable"
          />
        </v-col>
        <v-col
          cols="2"
        >
          Key to change <strong>{{ parameter.default }}</strong>:
        </v-col>
        <v-col
          v-if="parameter.type === 'query'"
          md="2"
        >
          <v-text-field
            v-model="parameter.key"
            label="Name of parameter"
          />
        </v-col>
        <v-col
          md="2"
        >
          <v-text-field
            v-model="parameter.value"
            label="Value to use"
          />
        </v-col>
      </v-row>
    </v-container>
  </v-list-item-content>
</template>

<script lang="ts">
import { Component, PropSync, Vue } from 'vue-property-decorator'

@Component
export default class QueryParameter extends Vue {
  @PropSync('value') public parameter!: Parameter // data is synced with LocationDecomposer
}

export interface Parameter {
  uid: number;
  type: string;
  changeable: boolean;
  key: string;
  value: string;
  default: string;
}

</script>

<style scoped>

</style>
