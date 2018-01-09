(function() {
    angular.module("synxApp")
        .controller("newTopicController", ["$scope", "subforumPromise", "previousState", "$state", "fileReaderService", function($scope, subforumPromise, previousState, $state, fileReaderService) {
            
            // subforum data inherited from parent state
            $scope.subforum = subforumPromise.data;

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