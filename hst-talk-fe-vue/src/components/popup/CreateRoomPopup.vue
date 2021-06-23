<template>
  <v-dialog v-model="isShowing" persistent max-width="290">
    <v-card>
      <v-card-title class="headline">Info</v-card-title>
      <v-card-text>
        You want create new room?
      </v-card-text>
      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn color="green darken-1" text @click="onClose();">No</v-btn>
        <v-btn color="green darken-1" text @click="onOk();">Yes</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
import { popup } from '@/mixins';

export default {
  mixins: [
    popup
  ]
}
</script>