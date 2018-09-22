new Vue({
  el: '#eventadd',
  data: {
	event: {
	  name: '',
	  date: '',
	  start_time: '',
	  end_time: '',
	}

  },
  methods: {
    post: function () {
  	  axios.post('/townbook/api/events', this.event)
	  .then(function (response) {
	    console.log(response);
	  })
	  .catch(function (error) {
	    console.log(error);
	  });
    }
  }
})