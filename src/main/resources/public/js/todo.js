angular.module('todoApp', [])
        .controller('TodoListController', function ($http) {
            var todoList = this;

            //todoList.todos = [
            //    {text: 'learn angular', done: true},
            //    {text: 'build an angular app', done: false}];

            $http.get('/api/todo').
                    success(function (data, status, headers, config) {
                        // this callback will be called asynchronously
                        // when the response is available
                        todoList.todos = data;
                    }).
                    error(function (data, status, headers, config) {
                        // called asynchronously if an error occurs
                        // or server returns response with an error status.
                        alert("Error: No data loaded from the db")
                    });

            todoList.addTodo = function () {

                $http.post('/api/todo', {text: todoList.todoText, done: false}).
                        success(function (data, status, headers, config) {
                            // this callback will be called asynchronously
                            // when the response is available
                            todoList.todos.push(data);
                            todoList.todoText = '';
                        }).
                        error(function (data, status, headers, config) {
                            alert("Error: No Insert")
                        });
            };

            todoList.remaining = function () {
                var count = 0;
                angular.forEach(todoList.todos, function (todo) {
                    count += todo.done ? 0 : 1;
                });
                return count;
            };

            todoList.archive = function () {
                var oldTodos = todoList.todos;
                todoList.todos = [];
                angular.forEach(oldTodos, function (todo) {
                    if (todo.done) {
                        $http.delete('/api/todo/' + todo.id).
                                success(function (data, status, headers, config) {}).
                                error(function (data, status, headers, config) {
                                    // called asynchronously if an error occurs
                                    // or server returns response with an error status.
                                    alert("Error:" + todo + " no removed.")
                                });
                    } else {
                        todoList.todos.push(todo);
                    }
                });
            };
        });