<template>
  <v-list-item color="primary" two-line>
    <v-list-item-content>
      <v-list-item-title><strong>{{chat.sender}}</strong></v-list-item-title>
      <v-list-item-subtitle>
        {{chat.message}} <small>{{chat.sendAt | moment('LT')}}</small>
      </v-list-item-subtitle>
    </v-list-item-content>    
  </v-list-item>
</template>

<script>
export default {
  name: 'ChatItem',
  props: {
    chat: Object
  }
}
</script>

<style>

</style>