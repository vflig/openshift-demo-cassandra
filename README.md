- Start Openshift
sudo ./openshift start

- Change access rights
sudo chmod a+rwX openshift.local.config/master/admin.kubeconfig ; sudo chmod +r openshift.local.config/master/openshift-registry.kubeconfig ; sudo chmod +r openshift.local.config/master/openshift-router.kubeconfig

- Start Docker registry
oc login
system:admin
oadm registry --create --credentials=openshift.local.config/master/openshift-registry.kubeconfig --config=openshift.local.config/master/admin.kubeconfig
oc describe service docker-registry --config=openshift.local.config/master/admin.kubeconfig

- Create Router
oadm router --create --credentials=openshift.local.config/master/openshift-router.kubeconfig --config=openshift.local.config/master/admin.kubeconfig

- Crate Persistent Volume
oc create -f demo/5_persistent_volume.json

oc edit scc restricted
- add line
allowPrivilegedContainer: true
- change runAsUser to:
type: RunAsAny


====================================
Start with Demo HERE
====================================

- Create Project
oc login
test-admin
oc new-project demo-application

- 1) Build
oc create -n demo-application -f demo/1_1_imagestream.json
oc create -n demo-application -f demo/1_2_build_todolist.json
- Show and start build in WebUI
﻿https://172.22.22.139:8443
- Show log output via
oc get pods
oc logs todolist-build-1-build

- 2) Deployment
docker ps
oc create -n demo-application -f demo/2_1_deploy_todolist.json
oc create -n demo-application -f demo/2_2_deploy_cassandra_node_1.json
oc create -n demo-application -f demo/2_3_deploy_cassandra_node_2.json
oc create -n demo-application -f demo/2_4_deploy_cassandra_node_3.json
- Show deployment config via web ui
docker ps

- 3) Service
oc create -n demo-application -f demo/3_1_service_frontend.json
oc create -n demo-application -f demo/3_2_service_database.json

- 4) Route
- Curl and show that the todolist is not accessable
curl http://my.todolist
oc create -n demo-application -f demo/4_route_todolist.json
- Curl and show that the todolist is accessable
curl http://my.todolist
curl --resolve todolist.one:80:172.22.22.139 http://todolist.one

- 5) Persistent Volume
https://docs.openshift.org/latest/admin_guide/persistent_storage_nfs.html
http://www.server-world.info/en/note?os=CentOS_7&p=nfs

- 6)
Kill and restart nodes
oc scale dc frontend --replicas=2
