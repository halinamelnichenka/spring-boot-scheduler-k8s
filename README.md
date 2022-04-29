#Steps to create spring-boot app in k8s:

```
mvn clean install

docker build -t spring-boot-scheduler-k8s:1 .

kubectl apply -f src/k8s/configMap.yaml

kubectl apply -f src/k8s/configMapCsv.yaml

kubectl apply -f src/k8s/service.yaml

kubectl apply -f src/k8s/deployment.yaml

```

#Steps to remove spring-boot app from k8s:

```
kubectl delete deploy demo-scheduler  

kubectl delete service demo-scheduler

kubectl delete configmap demo-scheduler

kubectl delete configmap csv-file

```