const app = Vue.createApp({
    data() {
        return {
            clients: [],
            firstName: "",
            lastName: "",
            email: "",
            html: "",

            
            isMenu: false,
            loanName: "",
            maxAmount: 0,
            paymentQuantity: 0,
            payments: [],
            payment: '',
            interest: 0,

            mensaje: ""

        }
    },
    created() {
        this.loadData();
    },
    methods: {
        loadData() {
            axios.get('http://localhost:8080/clients')
                .then(response => {
                    console.log(response);
                    this.clients = response.data._embedded.clients;
                    this.html = response.data._embedded;
                })
        },
        postClient() {
            axios.post('http://localhost:8080/clients', {
                    firstName: this.firstName,
                    lastName: this.lastName,
                    email: this.email
                })
                .then(response => {
                    this.loadData();

                });
        },
        addPayment() {
            if (this.payment < 1) {
                return swal("Wrong payment")
            }
            parseInt(this.payment)
            this.payments.push(parseInt(this.payment))
            this.payment = ''
        },
        reset() {
            this.loanName = ""
            this.maxAmount = 0
            this.paymentQuantity = 0
            this.payments = []
            this.payment = ''
            this.interest = 0
        },
        newLoan() {
            axios.post("/api/admin/loan", { name: this.loanName, maxAmount: parseInt(this.maxAmount), payments: this.payments, interest: parseInt(this.interest) })
                .then(res => swal('Loan created succefully'))
                .then(() => this.reset)
                .catch(() => swal('Incorrect data'))

        }
    },
    computed: {
        habilitar(){
            if(this.formElements.every(element=> element !== "")){
                return false
            } else {
                return true
            }
        },

        paymentsSorted() {
            return this.payments.sort((a, b) => a - b)
        }
    }

})
let consol = app.mount("#app")


