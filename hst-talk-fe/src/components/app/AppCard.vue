<template>
  <v-card>
    <v-toolbar :color="color" dark flat>
      <v-toolbar-title>
        <b>{{ title }}</b>
      </v-toolbar-title>
    </v-toolbar>
    <v-card-text>
      <slot />
    </v-card-text>
  </v-card>  
</template>

<script>
export default {
  name: 'AppCard',
  props: {
    color: {
      type: String,
      default: 'success'
    },
    title: {
      type: String,
      required: true
    }
  }
}
</script>

<style>

</style>