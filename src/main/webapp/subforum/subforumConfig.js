(function() {
    angular.module("synxApp")
        .config(["$stateProvider", function($stateProvider) {

            $stateProvider
                .state("subforums", {
                    url: "/subforums",
                    templateUrl: "subforum/subforums.html",
                    controller: "subforumsController"
                })

                .state("subforum", {
                    templateUrl: "subforum/subforum.html",
                    controller: "subforumController",
                    resolve: {
                        subforumPromise: function(subforumService, $transition$) {
                            return subforumService.getSubforum($transition$.params().title);
                        },
                        topicsPromise: function(topicService, $transition$) {
                            return topicService.getTopicsBySubforum($transition$.params().title);
                        }
                    }
                })
                
                .state("subforum.detail", {
                    url: "/subforums/{title}",
                    views: {
                        "header": {
                            templateUrl: "subforum/subforum-views/header.html"
                        },
                        "mainContent": {
                            templateUrl: "subforum/subforum-views/mainContent.html"
                        },
                        "sidebar": {
                            templateUrl: "subforum/subforum-views/sidebar.html"
                        }
                    }
                })

                .state("subforum.newtopic", {
                    url: "/newtopic",
                    views: {
                        "newtopic": {   
                            templateUrl: "topic/newTopicForm.html",
                            controller: "newTopicController"
                        }
                    },
                    resolve: {
                        previousState: ["$state", "$stateParams", function($state, $stateParams) {
                            console.log($state.current);
                            var currentStateData = {
                                name: $state.current.name,
                                params: $state.params,
                                url: $state.href($state.current.name, $state.params)
                            };
                            return currentStateData;
                        }]
                    }
                });
        }
        ]);
})();

