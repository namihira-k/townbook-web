var datetimeUtil = {
  methods: {
    
    isToday(str) {
      var startDay = new Date(str);
      var day = new Date();
      day.setHours(23);
      day.setMinutes(59);
      return startDay < day;
    },
    
  }
  
}