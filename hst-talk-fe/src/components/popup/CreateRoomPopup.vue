<template>
  <v-dialog v-model="visible" persistent max-width="290">
    <v-card>
      <v-card-title class="headline">Info</v-card-title>
      <v-card-text>
        You want create new room?
      </v-card-text>
      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn color="green darken-1" text @click="visible = false">No</v-btn>
        <v-btn color="green darken-1" text @click="visible = false">Yes</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
export default {
  props: {
    visible: Boolean
  }
}
</script>

<style>

</style>