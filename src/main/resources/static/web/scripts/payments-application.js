const app = Vue.createApp({
    data() {
        return {
          clients: [], 
          cards: [],
          numberCard: [],
          cvv:0,
          description: "",
          amount: 0,
          alert: false,
                     
          

        }
    },
    created() {
        this.loadData();
        
    },

    methods: {
        loadData() {
            axios.get("/api/clients/current")
                .then(response => {
                    console.log(response);
                    this.clients = response.data;
                    this.cards = response.data.card;                    
                })
        },
        loadPayments() {
            axios.post("/api/payment",
            {
                "number": this.numberCard,
                "cvv": this.cvv,
                "description": this.description,
                "amount": this.amount,
            },{headers:{'content-type':'application/json'}})
            .then(res => swal(
                'Su pago se a realizado con exito!',
                 'Puedes chequear tu cuenta.',
                 'success',), setTimeout(() => {
                     location.reload();
                 }, 3000))
                           
             .catch(res => console.log(res.response.data))
        },
        showModalAlert(e){
            console.log(e.target)
            this.alert = true;
        },
        showModalAlertB(e){
            console.log(e.target)
            this.alert = false;
        },
        
        
    
    
}





})
let consol = app.mount("#app")