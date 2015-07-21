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

- Create Project
oc login
test-admin
oc new-project todolist-project

- 1) Build
oc create -n todolist-project -f demo/1_1_imagestream.json
oc create -n todolist-project -f demo/1_2_build.json
- Show and start build in WebUI
﻿https://172.22.22.139:8443
- Show log output via
oc get pods
oc logs xxx

- 2) Deployment
oc create -n todolist-project -f demo/2_1_deploy_app.json
oc create -n todolist-project -f demo/2_3_deploy_cassandra_seed_1.json
oc create -n todolist-project -f demo/2_4_deploy_cassandra_seed_2.json
- Show deployment config via web ui

- 3) Service
oc create -n todolist-project -f demo/3_1_service_database.json
oc create -n todolist-project -f demo/3_2_service_app.json

- 4) Route
oc create -n todolist-project -f demo/4_route_app.json

curl http://todolist.one
curl --resolve todolist.one:80:172.22.22.139 http://todolist.one

- 5) Persistent Volume
https://docs.openshift.org/latest/admin_guide/persistent_storage_nfs.html
http://www.server-world.info/en/note?os=CentOS_7&p=nfs