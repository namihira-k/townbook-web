new Vue({
  el: '#header',
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
  	
  },
  
})