export default {
  data() {
    return {
      ui: {}
    }
  },
  methods: {
    initializePopup(popupNames) {
      popupNames.forEach(popupName => this.$set(this.ui, popupName, { isShowing: false }));
    },
    openPopup(popupName) {
      this.ui[popupName].isShowing = true;
    },
    closePopup(popupName) {
      this.ui[popupName].isShowing = false;
    },    
  }
}