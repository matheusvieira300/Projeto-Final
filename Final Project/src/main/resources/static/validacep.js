function buscarEndereco() {
    var cep = document.getElementsByName('cep')[0].value;
    var url = 'https://viacep.com.br/ws/' + cep + '/json/';

    fetch(url)
        .then(response => response.json())
        .then(data => preencherCamposEndereco(data))
        .catch(error => exibirErroCEP());
}

function preencherCamposEndereco(data) {
    if (!data.erro) {
        document.getElementById('logradouro').value = data.logradouro;
        document.getElementById('complemento').value = data.complemento;
        document.getElementById('bairro').value = data.bairro;
        document.getElementById('localidade').value = data.localidade;
        document.getElementById('uf').value = data.uf;
        document.getElementById('cep-erro').classList.add('d-none');
    } else {
        exibirErroCEP();
    }
}

function exibirErroCEP() {
    document.getElementById('cep-erro').classList.remove('d-none');
}