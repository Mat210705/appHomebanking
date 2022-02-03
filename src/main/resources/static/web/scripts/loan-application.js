const app = Vue.createApp({
    data() {
        return {
          loans:[],
          clients: [], 
          accounts: [],           
          payments: [],
          loanSelect: [],  
          loansFilter: [],
          cuotas: [],
          paymentsSelect: 0,
          amountSelect: 0,
          totalAmount: 0,
          accountSelect: [],
          alert: false,

        }
    },
    created() {
        this.loadData();
        this.loadLoans();
    },

    methods: {
        loadData() {
            axios.get("/api/clients/current")
                .then(response => {
                    console.log(response);
                    this.clients = response.data;
                    this.accounts = response.data.accounts;                    
                })
        },
        realizarPrestamo(){
            axios.post('/api/loans',
            {
                "name": this.loanSelect.name,
                "amount": this.amountSelect,
                "payments": this.paymentsSelect,
                "accountDestiny": this.accountSelect,
            },{headers:{'content-type':'application/json'}})
            .then(res => swal(
                'Prestamo Otorgado con éxito!',
                 'Puedes chequear tu cuenta.',
                 'success',), setTimeout(() => {
                     location.reload();
                 }, 3000))
                           
             .catch(res => console.log(res.response.data))
        },
        loadLoans(){
            axios.get("/api/loans")
            .then(response => {
                console.log(response);
                this.loans = response.data;                     
            })
        },
        showModalAlert(e){
            console.log(e.target)
            this.alert = true;
        },
        showModalAlertB(e){
            console.log(e.target)
            this.alert = false;
        },
    computed:{
        totalAmountFunction(){            
           return this.totalAmount = this.amountSelect * 1.20;
        }
    }
    
    
}





})
let consol = app.mount("#app")