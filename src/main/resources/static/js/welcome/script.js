new Vue({
  el: '#app',
  
  mixins: [commonUtil, datetimeUtil],
  
  data () {
    return {
      stationStats: [],
      eventStats: [],
      
      prefectures: [],
      events: [],
      
      recommendEvents: [],
    };
  },

  computed: {
      hotEvent: function() {
        return this.events[0];
      },
      
      recommendEvent: function() {
        if (this.recommendEvents.length > 0) {
          return this.recommendEvents[0];
        } else {
          return this.events[1];
        }
      },
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
              size: 2,
              eventTypes: ['FREE'],
            },
            paramsSerializer: function(params) {
              return Qs.stringify(params, {arrayFormat: 'repeat'})
            }
         })
         .then(res => {
            this.events = res.data.results;
         });
    
    axios.get('/yorimichi/api/events/recommended', {
              params: {
                size: 1,
              }
          })
         .then(res => {
             this.recommendEvents = res.data.results;
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