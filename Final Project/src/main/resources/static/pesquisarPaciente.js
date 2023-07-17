document.getElementById('btnPesquisar').addEventListener('click', pesquisarPacientes);

function pesquisarPacientes() {
    var barraDePesquisa = document.getElementById('barraDePesquisa');
    var nome = barraDePesquisa.value.toUpperCase();

    var linhas = document.getElementsByClassName('nome-paciente');

    for (var i = 0; i < linhas.length; i++) {
        var nomePaciente = linhas[i].textContent || linhas[i].innerText;
        nomePaciente = nomePaciente.toUpperCase();

        if (nome === '' || nomePaciente.indexOf(nome) > -1) {
            linhas[i].parentNode.style.display = '';
        } else {
            linhas[i].parentNode.style.display = 'none';
        }
    }
}
