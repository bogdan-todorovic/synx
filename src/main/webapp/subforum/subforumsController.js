(function() {
    angular.module("synxApp")
        .controller("subforumsController", ["$scope", "$rootScope", "fileReaderService", "subforumService", "userService", function($scope, $rootScope, fileReaderService, subforumService, userService) {

                // Load all subforums
                subforumService.getAllSubforums()
                    .then(function(response) {
                            $scope.subforums = response.data;
                        },
                        function(error) {
                            console.log("Unable to load subforums");
                        }
                    );
                
                // load all moderators
                userService.getAllModerators()
                    .then(function(response) {
                            $scope.moderators = response.data;
                        }, 
                        function(error) {
                            console.log(error);
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
                }

                // method which removes previously added rule from the rules list
                $scope.removeRule = function(index) {
                    $scope.newSf.rules.splice(index, 1);
                }

                // managing list of selected/added moderators
                $scope.newSf.moderators = [];
                // toggle selection for a given moderator by username
                $scope.toggleModeratorSelection = function(moderator) {
                    var index = $scope.newSf.moderators.indexOf(moderator);
                    
                    // Is currently selected
                    if(index !== -1) {
                        $scope.newSf.moderators.splice(index, 1);
                    }
                     // Is newly selected
                    else { 
                        $scope.newSf.moderators.push(moderator);
                    }

                    console.log($scope);
                }

                $scope.create = function() {
                    subforumService.createSubforum($scope.newSf)
                        .then(function(response) {
                            $scope.subforums.push(response.data);
                        }, function(error) {
                            $scope.alertMessage = "Subforum with that title already exists";
                            console.log(error.data);
                        });
                }
            }
        ]);
})();