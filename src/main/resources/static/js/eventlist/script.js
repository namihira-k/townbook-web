new Vue({
  el: '#app',
  
  mixins: [commonUtil, datetimeUtil],
  
  data () {
    return {
      events: [],
      fromDate: '',
      prefectureCode: prefectureCode,
      stationCode: stationCode,
      prefectures: [],
      lines: [],		
      stations: [],

      totalCount: 0,
      page: 0,

      infoUrl: '',

      isProcess: false,
    };
  },
  
  mounted () {
    axios.get('/yorimichi/api/prefectures', {
            params: {
              hasEvents: true,
            }})
         .then(res => { this.prefectures = res.data.results; });    

    axios.get('/yorimichi/api/stations', {
            params: {
              prefectureCode: this.prefectureCode
            }})
         .then(res => { this.stations = res.data.results; });  	

    axios.get('/yorimichi/api/events', {
            params: {
              prefectureCode: this.prefectureCode,
              stationCode: this.stationCode,
         }})
         .then(res => {
             this.totalCount = res.data.totalCount;
             this.events = res.data.results;
         });
  },
  
  updated () {
    this.format();  	
  },
  
  methods: {

    init () {
      this.events = [];
      this.prefectureCode = 13;
      this.setStations();

      this.getLines();
      this.getEvents();
      this.moveSearch();
    },

    search () {
      this.moveEventList();
      this.getEvents();
    },

    setStations () {
      this.getStations();
      this.stationCode = '';
    },

    getStations () {
      axios.get('/yorimichi/api/stations', {
        params: {
          prefectureCode: this.prefectureCode
        }
      }).then(res => { this.stations = res.data.results; });
    },
    
    getEvents () {
      this.isProcess = true;

      this.page = 0;
      this.fromDate = M.Datepicker.getInstance($('#startDate')).el.value;    	
      axios.get('/yorimichi/api/events', {
              params: {
                page: this.page,
                prefectureCode: this.prefectureCode,        	
                stationCode: this.stationCode,
                fromDate: this.fromDate,
           }})
           .then(res => {
             this.totalCount = res.data.totalCount;
             this.events = res.data.results;
             this.format();
           })
           .then(() => { this.isProcess = false; });
    },
    
    addEvents ($state) {
      this.page += 1;
      axios.get('/yorimichi/api/events', {
              params: {
                page: this.page,
                prefectureCode: this.prefectureCode,
                stationCode: this.stationCode,
                fromDate: this.fromDate
              }})
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
    
    moveSearch () {
      $("html,body").animate({scrollTop:0}, "fast");    	
    },
    
    moveEventList () {
      var el = $('#btn-search').offset().top;
      $("html,body").animate({scrollTop:el}, "slow");    	
    },
    
    format () {
      $('.url-content').linkify({
        target: '_blank',
      });
    },
        
  }
});