(function() {
    angular.module("synxApp")
        .controller("newTopicController", ["$scope", "previousState", "$state", "fileReaderService", function($scope, previousState, $state, fileReaderService) {
            
            // subforum data inherited from parent state
            console.log($scope.subforum);

            // on form close redirect to subforum page
            $scope.closeForm = function() {
                $state.go(previousState.name, previousState.params)
            }

            $scope.topic = {};

            // the file as a URL 
            // representing the file's data as a base64 encoded string
            $scope.loadFile = function (file) {
                fileReaderService.readAsDataUrl(file, $scope)
                    .then(function(result) {
                        $scope.topic.content = result;
                    });
            };
        }]);
})();