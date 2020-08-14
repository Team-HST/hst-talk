export default {
  props: {
    isShowing: Boolean
  },
  methods: {
    onClose() {
      this.$emit('close');
    },
    onOk() {
      this.$emit('ok');
    },
  }
}