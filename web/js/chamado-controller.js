// adicionado o AppM  odule para 'herdar' a lista de chamados
angular.module('ChamadoModule', ['CrudServiceModule'])
        
.controller('ChamadoController', ['$scope', 'CrudService','$http', function($scope, CrudService, $http) {

                $scope.url = 'http://localhost:8080/SysProtekoWebChamados_FULL/rest/chamados';
    
                $scope.objeto = {chamado : ''};
                $scope.chamados = {};
                //$scope.resultados;
                $scope.editarRegistroChamado = false;
                $scope.resultadoUnidadeChamado;
                $scope.unidadeRecebida;

                $scope.selecionarUnidade = {
                    unidadeDisponivel: null,
                    unidadeSelecionada: null
                };
                
                $scope.selecionarTecnico = {
                    tecnicoDisponivel: null,
                    tecnicoSelecionado: null
                };
                
                    $scope.listar = function () {
                    $http({
                        method: 'GET',
                        url: 'http://localhost:8080/SysProtekoWebChamados_FULL/rest/chamados'
                    }).then(function (response) {
                        $scope.chamados.lista = response.data;
                        
                        if ($scope.chamados.lista.length > 0) {
                            $scope.existemDados = true;
                        } else {
                            $scope.existemDados = false;
                        }
                    }, function (response) {
                        console.log(response);
                    });
                };


                $scope.pesquisar = function () {
                    CrudService.pesquisar($scope.url, $scope.objeto.nChamado, function(data) {
                    $scope.resultados = data;
                });
                };

                $scope.consultar = function (nChamado) {
                    CrudService.consultar($scope.url, nChamado, function(obj) {
                    $scope.objeto = obj;
                    $scope.editarRegistroChamado = true;
                    });
                };
                
                function dataAtualFormatada(){
                    var data = new Date();
                    var dia = data.getDate();
                    if(dia.toString().length == 1){
                        dia = "0" + dia;
                    }
                    var mes = data.getMonth() + 1;
                    var ano = data.getFullYear();
                    return dia + "/" + mes + "/" + ano;
                }
                
                $scope.excluir = function () {
                   CrudService.excluir($scope.url, obj.nChamado, function() {
                    $scope.limpar();
                    CrudService.geraMensagemDefault();
                });
                };

                $scope.salvar = function () {
                    $scope.objeto.unidade = $scope.selecionarUnidade.unidadeSelecionada;
                    $scope.objeto.tecnico = $scope.selecionarTecnico.tecnicoSelecionado;
                    console.log($scope.objeto.unidade);
                    console.log($scope.objeto.tecnico);
                    $scope.objeto.unidade = JSON.parse($scope.objeto.unidade);
                    $scope.objeto.tecnico = JSON.parse($scope.objeto.tecnico);
                    console.log("OBJETO A SALVAR" + $scope.objeto);
                    CrudService.salvar($scope.url, $scope.objeto, function(location) {
                    console.log(location);
                    CrudService.geraMensagemDefault();
                    $scope.redir('/chamado-lista');
                });
                };

                $scope.alterar = function () {
                    CrudService.alterar($scope.url, $scope.objeto, function(location) {
                        console.log(location);
                        CrudService.geraMensagemDefault();
                        $scope.editarRegistroChamado = false;
                    });
                };
                
                $scope.limpar = function () {
                    $scope.chamado;
                    $scope.chamados = {};
                    $scope.listar();
                };

                $scope.cancelarEdicao = function () {
                    $scope.editarRegistroChamado = false;
                    $scope.limpar();
                };
                
                $scope.listarUnidade = function() {
                    CrudService.pesquisar('http://localhost:8080/SysProtekoWebChamados_FULL/rest/unidades', 
                     "", function(data) {
                    $scope.selecionarUnidade.unidadeDisponivel = data;
                    console.log(data);

                 });
                 };
                 
                 $scope.buscarUnidadeChamado = function(){
                    CrudService.pesquisar($scope.url, $scope.objeto.nChamado, function(data) {
                    $scope.resultadoUnidadeChamado = data;
                    $scope.unidadeRecebida = data.idUnidade;
                });
                 };
                 
                 $scope.listarTecnicos = function() {
                     
                    CrudService.pesquisar('http://localhost:8080/SysProtekoWebChamados_FULL/rest/tecnicos',
                    "", function (data){
                       $scope.selecionarTecnico.tecnicoDisponivel = data;
                       console.log($scope.selecionarTecnico.tecnicoDisponivel);
                    });

                
                 };



            }]);