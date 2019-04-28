new Vue({
  el: '#app',
  
  mixins: [commonUtil, datetimeUtil],
  
  data () {
    return {
      stationStats: [],
      eventStats: [],
      
      prefectures: [],
      events: [],
      
      recommendedEvents: [],
    };
  },

  mounted () {
    axios.get('/yorimichi/api/prefectures', {
            params: {
              hasEvents: true,
            }})
         .then(res => { this.prefectures = res.data.results; });    
    
    axios.get('/yorimichi/api/stats/station')
         .then(res => { this.stationStats = res.data.results; });

    axios.get('/yorimichi/api/stats/event')
         .then(res => { this.eventStats = res.data; });
    
    axios.get('/yorimichi/api/eventsearch', {
            params: {
              size: 1,
              eventTypes: ['FREE'],
            },
            paramsSerializer: function(params) {
              return Qs.stringify(params, {arrayFormat: 'repeat'})
            }
         })
         .then(res => {
            this.events = res.data.results;
         });
    
  },
  
  methods: {
    hasEvents(prefectureCode) {
      for(let s of this.stationStats) {
        if (s.prefectureCode == prefectureCode) {
          return true;
        }
      };
      return false;
    },
  },

})