function confirmarLogout() {
    Swal.fire({ // Swal (SweetAlert) pra utilizar a biblioteca
        title: 'Confirmação',
        text: 'Deseja realmente fazer logout?',
        icon: 'question',
        showCancelButton: true,
        confirmButtonText: 'Sim',
        cancelButtonText: 'Não'
    }).then((resultado) => {
        if (resultado.value) {

            window.location.href = '/logout';
        }
    });
}