const app = Vue.createApp({
    data() {
        return {
            client: [],
            clients: [],
            accounts: [],            
            id: 0,           
            Debit: true,
            accountNumber: "",
            desdeFecha: "",
            hastaFecha:"",
           
            




        }
    },
    created() {
        this.loadData();
        this.loadDataClient();
    
    },

    methods: {
        loadData() {
            const urlParams = new URLSearchParams(window.location.search);
            console.log(urlParams);
            const myParam = urlParams.get('id');
            console.log(myParam);
            this.id = myParam;

            axios.get(`/api/accounts/${this.id}`)
                .then(response => {
                    console.log(response);
                    this.clients = response.data;
                    this.accountNumber = response.data.number;
                    this.balance = response.data.balance;                                      
                    this.amount = response.data.transaction;
                    
                         
                })

        },
        logOut(e){
            e.preventDefault() 
           axios.post('/api/logout')
           .then(response => window.location.href = ("/web/index.html"))
        },
        loadDataClient() {
            axios.get(`/api/clients/current`)
                .then(response => {
                    console.log(response);
                    this.client = response.data;
                    this.accounts = response.data.accounts;
                                                                                                                   
                })
        },
        formateoFecha(date) {
            return new Date(date).toLocaleDateString('en-gb');
        },
        importPDF(){
            if (this.desdeFecha == "") {
                return swal("Necesitas establecer una fecha de inicio")
            }
            if (this.hastaFecha == "") {
                this.hastaFecha = new Date(Date.now()).toLocaleDateString('en-gb').split("/").reverse().join("-")
            }
            axios({
                url: '/api/transaction/export/pdf',
                method: 'POST',
                responseType: 'blob',
                data: {
                    desdeFecha: this.desdeFecha.replace('/', '-'),
                    hastaFecha: this.hastaFecha.replace('/', '-'),
                    accountNumber: this.accountNumber
                }
            })
                .then((response) => {
                    const url = window.URL.createObjectURL(new Blob([response.data]));
                    const link = document.createElement('a');
                    link.href = url;
                    link.setAttribute('download', 'Transactionsfrom' + this.desdeFecha + 'to' + this.hastaFecha + '.pdf');
                    document.body.appendChild(link);
                    link.click();
                })
                .catch(err => swal(err))
        }
       
     


    }






})
let consol = app.mount("#app")