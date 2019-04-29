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
      isRecommended: false,
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
              size: 3,
            },
         })
         .then(res => {
            this.events = res.data.results.filter(r => r.uuid !== this.uuid);
            if (3 <= this.events.length) {
              this.events.length = 2;
            }
         });
  },
    
  updated () {
    this.format();
  },
  
  methods: {
    recommend () {
      this.isProcess = true;
      var path = '/yorimichi/api/events/' + uuid + '/recommend';
      axios.post(path, {})
           .then(() => {
              this.isRecommended = true;
              this.isProcess = false;
           });
    },
    
    format () {
      $('.url-content').linkify({
        target: '_blank',
      });
    },
        
    isHot(event) {
      if (!event) {
        return;
      }
      
      return event.isFree || this.isToday(event.startDateTime);
    },
     
  }
  
})