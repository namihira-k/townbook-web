new Vue({
  el: '#app',
  
  mixins: [commonUtil, datetimeUtil],
  
  data () {
    return {
      uuid: uuid,
      event: null,
      prefectures: [],
      events: [],
      
      prefectureCode: prefectureCode,
      stationCode: stationCode,
      
      isProcess: true,
    };
  },
    
  mounted () {
    axios.get('/yorimichi/api/prefectures')
         .then(res => { this.prefectures = res.data.results; });    

    axios.get('/yorimichi/api/events/' + this.uuid)
         .then(res => {
           this.event = res.data;
         })
         .then(() => {
           this.isProcess = false;
         });
    
    axios.get('/yorimichi/api/events', {
            params: {
              prefectureCode: this.prefectureCode,
              stationCode: this.stationCode,
              page: 0,
              size: 2,
            },
         })
         .then(res => {
            this.events = res.data.results;
         });
  },
    
  updated () {
    this.format();
  },
  
  methods: {
    format () {
      $('.url-content').linkify({
        target: '_blank',
      });
    },
        
    isHot(event) {
      return event.isFree || this.isToday(event.startDateTime);
    },
     
  }
  
})