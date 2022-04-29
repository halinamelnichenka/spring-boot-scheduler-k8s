#Using kubectl

### 1. Steps to create spring-boot app in k8s:

The following steps should be executed from the project root directory:
```
mvn clean install

docker build -t spring-boot-scheduler-k8s:1 .

kubectl apply -f src/k8s/configMap.yaml

kubectl apply -f src/k8s/configMapCsv.yaml

kubectl apply -f src/k8s/service.yaml

kubectl apply -f src/k8s/deployment.yaml

```

### 2. Steps to check app logs:

1. Check what pods are running:
```
kubectl get pods
```

Output should be similar to:
```
NAME                              READY   STATUS    RESTARTS   AGE
demo-scheduler-5654cb6f56-wz5fq   1/1     Running   0          2m22s
```

2. Then manually copy pod name (ex: demo-scheduler-5654cb6f56-wz5fq)

3. Check out pod's logs:
```
kubectl logs <insert-pod-name>
```

Csv content should be present in the logs. Similar to:
```
2022-04-29 11:47:54.712  INFO 1 --- [   scheduling-1] c.e.s.scheduler.SandboxScheduler         : Csv: 
time,message
2022-01-01,text1
2022-01-02,text2

```
### 3. Steps to remove spring-boot app from k8s:

```
kubectl delete deploy demo-scheduler  

kubectl delete service demo-scheduler

kubectl delete configmap demo-scheduler

kubectl delete configmap csv-file

```

#Using helm

1. Install and configure helm for mac: 
```
brew install helm
```

2. mvn clean install

docker build -t spring-boot-scheduler-k8s:1 .

helm install demo-scheduler ./src/helm/springbootschedulerk8s

helm uninstall demo-scheduler
3. 