(function() {
    angular.module("synxApp")
        .controller("newTopicController", ["$scope", "$rootScope", "previousState", "$state", "fileReaderService", "topicService", function($scope, $rootScope, previousState, $state, fileReaderService, topicService) {
            
            previousState.params.title = $scope.subforum.title;
            // on form close redirect to subforum page
            $scope.closeForm = function() {
                $state.go(previousState.name, previousState.params);
            }

            $scope.topic = {};
            // getting subforum title from parent scope
            $scope.topic.subforum = $scope.subforum.title;
            // the file as a URL 
            // representing the file's data as a base64 encoded string
            $scope.loadFile = function (file) {
                fileReaderService.readAsDataUrl(file, $scope)
                    .then(function(result) {
                        $scope.topic.content = result;
                    });
            };

            $scope.submit = function() {
                $scope.topic.author = $rootScope.user.username;
                topicService.createTopic($scope.topic)
                    .then(function(response) {
                        // push created topic into the parent's topics collection
                        // and redirect to the parent state
                        $scope.topics.push(response.data);
                        $state.go(previousState.name, previousState.params)
                    }, function(error) {
                        console.log(error);
                    });
            };
        }]);
})();