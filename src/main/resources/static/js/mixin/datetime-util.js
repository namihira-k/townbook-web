var datetimeUtil = {
    
  data () {
    return {
      datetime: '---------- --:--',
    };
  },

  mounted () {
    this.updateTime();
    let self = this;
    setInterval(function () {self.updateTime()}, 60000);
  },
    
  methods: {
    
    isToday(str) {
      var startDay = new Date(str);
      var day = new Date();
      day.setHours(23);
      day.setMinutes(59);
      return startDay < day;
    },
    
    updateTime () {
      this.datetime = this.formatDate(new Date());
    },
  
    formatDate (date) {
      return date.getFullYear() + "-" + this.formatDigit(date.getMonth() + 1) + "-" + this.formatDigit(date.getDate()) + " " 
             + this.formatDigit(date.getHours()) + ":" + this.formatDigit(date.getMinutes());
    },
  
    formatDigit (val) {
      return ("0" + val).slice(-2);
    },
  }
  
}