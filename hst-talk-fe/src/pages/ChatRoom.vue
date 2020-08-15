<template>
  <v-app>
    <v-container>
      <h1>ChatRoom!</h1>
      <h2>RoomId: {{roomId}} <span>copy this!</span></h2>

    </v-container>
  </v-app>
</template>

<script>
export default {
  name:  'ChatRoom',
  props: {
    roomId: {
      type: String,
      required: true
    }
  },
  methods: {

  }
}
</script>

<style>

</style>