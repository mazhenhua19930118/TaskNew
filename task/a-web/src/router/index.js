import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import TaskMain from '@/components/TaskMain'
import assignment from '@/components/assignment'
import taskList from '@/components/taskList'
import taskprogress from '@/components/taskprogress'
import assignmanage from '@/components/assignmanage'
import taskListcopy from '@/components/taskListcopy'
import taskListcopycopy from '@/components/taskListcopycopy'
import taskListcopycopycopy from '@/components/taskListcopycopycopy'



Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld
    },
    {
      path: '/TaskMain',
      name: 'TaskMain',
      component: TaskMain,
      children: [{
        path: 'assignment',
        name: 'assignment',
        component: assignment
      },
      {
        path: 'taskList',
        name: 'taskList',
        component: taskList
      },
      {
        path: 'taskprogress',
        name: 'taskprogress',
        component: taskprogress
      },
      {
        path: 'assignmanage',
        name: 'assignmanage',
        component: assignmanage
      },
      {
        path: 'taskListcopy',
        name: 'taskListcopy',
        component: taskListcopy
      },
      {
        path: 'taskListcopycopy',
        name: 'taskListcopycopy',
        component: taskListcopycopy
      },
      {
        path: 'taskListcopycopycopy',
        name: 'taskListcopycopycopy',
        component: taskListcopycopycopy
      },
    ]
    },

  ]
})
