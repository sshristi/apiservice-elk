Elastic Search config:

1. Install Elastic Search / Kibana from https://www.elastic.co/start
2. go to elastic> config> **elasticsearch.yml**
3. Give a cluster name:
```
# ---------------------------------- Cluster -----------------------------------
#
# Use a descriptive name for your cluster:
#
cluster.name: generic-tool-javaelk
#
```


4. give path for the spring-boot project
```
# ----------------------------------- Paths ------------------------------------
#
# Path to directory where to store the data (separate multiple locations by comma):
#
path.data: D:\springboot-elk
#
```

5. run elasticsearch>bin> **elasticsearch** in cmd
6. Start ***spring-boot project***
