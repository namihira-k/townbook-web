new Vue({
  el: '#app',
  
  mixins: [commonUtil, datetimeUtil],
  
  data () {
    return {
      events: [],
      fromDate: '',
      prefectureCode: prefectureCode,
      stationCode: stationCode,
      category: ['MUSIC', 'BOOK'],

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
    axios.get('/api/prefectures', {
            params: {
              hasEvents: true,
            }})
         .then(res => { this.prefectures = res.data.results; });    

    axios.get('/api/stations', {
            params: {
              prefectureCode: this.prefectureCode
            }})
         .then(res => { this.stations = res.data.results; });  	

    axios.get('/api/events', {
            params: {
              prefectureCode: this.prefectureCode,
              stationCode: this.stationCode,
              category: this.category,
            },
            paramsSerializer: function(params) {
              return Qs.stringify(params, {arrayFormat: 'repeat'})
            }
         })
         .then(res => {
            this.totalCount = res.data.totalCount;
            this.events = res.data.results;
         });
    
    M.toast({
      html: this.datetime,
      classes: 'rounded orange',
      displayLength: 60000,
    });
    
    let self = this;
    setInterval(function () {
      self.updateTime();
      
      M.Toast.dismissAll();     
      M.toast({
        html: self.datetime,
        classes: 'rounded orange',
        displayLength: 60000,
      });
    }, 60000);
  },
  
  updated () {
    this.format();
  },
  
  methods: {

    init () {
      this.events = [];
      this.prefectureCode = 13;
      this.setStations();
      this.category = ['MUSIC', 'BOOK'];

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
      axios.get('/api/stations', {
        params: {
          prefectureCode: this.prefectureCode
        }
      }).then(res => { this.stations = res.data.results; });
    },
    
    getEvents () {
      this.isProcess = true;

      this.page = 0;
      this.fromDate = M.Datepicker.getInstance($('#startDate')).el.value;    	
      axios.get('/api/events', {
              params: {
                page: this.page,
                prefectureCode: this.prefectureCode,
                stationCode: this.stationCode,
                category: this.category,
                fromDate: this.fromDate,
              },
              paramsSerializer: function(params) {
                return Qs.stringify(params, {arrayFormat: 'repeat'})
              }
            })
           .then(res => {
             this.totalCount = res.data.totalCount;
             this.events = res.data.results;
             this.format();
           })
           .then(() => { this.isProcess = false; });
    },
    
    addEvents ($state) {
      this.page += 1;
      axios.get('/api/events', {
              params: {
                page: this.page,
                prefectureCode: this.prefectureCode,
                stationCode: this.stationCode,
                category: this.category,
                fromDate: this.fromDate
              },
              paramsSerializer: function(params) {
                return Qs.stringify(params, {arrayFormat: 'repeat'})
              }
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