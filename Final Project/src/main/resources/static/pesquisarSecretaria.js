document.getElementById('btnPesquisar').addEventListener('click', pesquisarSecretarias);

function pesquisarSecretarias() {
    var barraDePesquisa = document.getElementById('barraDePesquisa');
    var nome = barraDePesquisa.value.toUpperCase();

    var linhas = document.getElementsByClassName('nome-secretaria');

    for (var i = 0; i < linhas.length; i++) {
        var nomeSecretaria = linhas[i].textContent || linhas[i].innerText;
        nomeSecretaria = nomeSecretaria.toUpperCase();

        if (nome === '' || nomeSecretaria.indexOf(nome) > -1) {
            linhas[i].parentNode.style.display = '';
        } else {
            linhas[i].parentNode.style.display = 'none';
        }
    }
}
