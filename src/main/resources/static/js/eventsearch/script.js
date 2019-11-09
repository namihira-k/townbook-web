new Vue({
  el: '#app',
    
  mixins: [commonUtil, datetimeUtil],
  
  data () {
    return {
      events: [],
      q: q,

      prefectures: [],      
      
      totalCount: 0,
      page: 0,      
      
      isProcess: false,
    }
  },
  
  mounted () {
    axios.get('/api/prefectures', {
            params: {
              hasEvents: true,
            }})
         .then(res => { this.prefectures = res.data.results; });    
    
    axios.get('/api/eventsearch', {
            params: {
              q: this.q,
            },
          })
          .then(res => {
            this.totalCount = res.data.totalCount;
            this.events = res.data.results;
          });
  },
  
  methods: {

    addEvents ($state) {
      this.page += 1;
      axios.get('/api/eventsearch', {
              params: {
                page: this.page,
                q: this.q,
              },
            })
           .then(res => {
              if (res.data.results.length > 0) {
                this.events.push(...res.data.results);
                this.format();
                $state.loaded();
              } else {
                $state.complete();
              }
      });
    },
    
    isHot(event) {
      return event.isFree;
    },
  
  },
  
});