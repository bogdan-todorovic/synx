(function() {
    angular.module("synxApp")
        .controller("subforumsController", ["$scope", "$rootScope", "fileReaderService", "subforumService",
            function($scope, $rootScope, fileReaderService, subforumService) {

                // Load all subforums
                subforumService.getAllSubforums()
                    .then(function(response) {
                            $scope.subforums = response.data;
                        },
                        function() {
                            console.log("Unable to load subforums");
                        }
                    );
                 
                 // instance for new subforum
                $scope.newSf = {};
                

                // the file as a URL 
                // representing the file's data as a base64 encoded string
                $scope.loadFile = function () {
                    fileReaderService.readAsDataUrl($scope.chosenFile, $scope)
                        .then(function(result) {
                            $scope.newSf.icon = result;
                        });
                };

                // method which adds new text input field on button click
                // and adds the rule into the rules list
                $scope.newSf.rules = [];
                $scope.addNewRule = function() {
                    $scope.newSf.rules.push("");
                    console.log($scope.newSf.rules);
                }

                // method which removes previously added rule from the rules list
                $scope.removeRule = function(index) {
                    $scope.newSf.rules.splice(index, 1);
                }

                $scope.create = function() {
                    subforumService.createSubforum($scope.newSf)
                        .then(function(response) {
                            console.log("succesfully created");
                        }, function(error) {
                            $scope.alertMessage = "Subforum with that title already exists";
                            console.log(error.data);
                        });
                }
            }
        ]);
})();