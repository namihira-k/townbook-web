new Vue({
  el: '#app',
  data () {
    return {
      event: {
        name: '',
        url: '',
      },
      isProcess: false,
      isDone: false,
    };
  },

  methods: {
    post () {
      this.isProcess = true;
      axios.post('/api/eventrequests', this.event)
           .then(() => {
              this.isProcess = false;
              this.isDone = true;
           });
    },
    
    isReady () {
      return 0 < this.event.name.length && 0 < this.event.url.length; 
    }
  }
})