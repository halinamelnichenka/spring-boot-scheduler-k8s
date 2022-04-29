# Spring-boot-scheduler POC

This PoC is a simple SpringBoot application with a Scheduler that runs every 10 seconds and 
1) reads a file from `/csv/temp.csv`
2) outputs the file content to logs.

If you run the app locally, this file will be read: [csv from project root](csv/temp.csv).


If you run the app using helm, this file will be read: [csv from helm folder](src/helm/springbootschedulerk8s/csv/temp.csv)

#How to run using helm

Prerequisite: Install and configure helm for mac:
```
brew install helm
```

The following commands should be run from the project root directory:
1. Build the project: `mvn clean install`
2. Build docker image: `docker build -t spring-boot-scheduler-k8s:1 .`
3. Install helm release: `helm install demo-scheduler ./src/helm/springbootschedulerk8s`
4. Check what pods are running: `kubectl get pods`

Output should be similar to:
```
NAME                              READY   STATUS    RESTARTS   AGE
demo-scheduler-5654cb6f56-wz5fq   1/1     Running   0          2m22s
```

5. Then manually copy pod name (ex: demo-scheduler-5654cb6f56-wz5fq)

6. Check out pod's logs: `kubectl logs <insert-pod-name>`

Csv content should be present in the logs. Similar to:
```
2022-04-29 11:47:54.712  INFO 1 --- [   scheduling-1] c.e.s.scheduler.SandboxScheduler         : Csv: 
time,message
2022-01-01,text1
2022-01-02,text2

```

7. When you no longer need the pods, you can uninstall helm release using: `helm uninstall demo-scheduler`


#How to run using kubectl


The following steps should be executed from the project root directory:
1. Build the project: `mvn clean install`
2. Build docker image: `docker build -t spring-boot-scheduler-k8s:1 .`
3. Add kubernetes config map for app configuration: `kubectl apply -f src/k8s/configMap.yaml`
4. Add kubernetes config map to move csv file content into configMap: `kubectl apply -f src/k8s/configMapCsv.yaml`
5. Add kubernetes service: `kubectl apply -f src/k8s/service.yaml`
6. Add kubernetes deployment: `kubectl apply -f src/k8s/deployment.yaml`
7. Check what pods are running: `kubectl get pods`

Output should be similar to:
```
NAME                              READY   STATUS    RESTARTS   AGE
demo-scheduler-5654cb6f56-wz5fq   1/1     Running   0          2m22s
```

8. Then manually copy pod name (ex: demo-scheduler-5654cb6f56-wz5fq)

9. Check out pod's logs: `kubectl logs <insert-pod-name>`

Csv content should be present in the logs. Similar to:
```
2022-04-29 11:47:54.712  INFO 1 --- [   scheduling-1] c.e.s.scheduler.SandboxScheduler         : Csv: 
time,message
2022-01-01,text1
2022-01-02,text2

```
10. When you no longer need the pods, you can uninstall everything using the commands below:

```
kubectl delete deploy demo-scheduler  

kubectl delete service demo-scheduler

kubectl delete configmap demo-scheduler

kubectl delete configmap csv-file

```
