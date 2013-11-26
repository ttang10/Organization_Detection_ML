project
=======

To run this project, please find the project / OrgDetec / OrgDetec / execute / Executor.java file and set the NORMALIZED_MIN__OCC_FAC factor (m factor in documentation) and NORMALIZED_MIN__DIF_FAC (n factor in documentation) the value you like. Make sure you have the file of celebrities_profiles.txt in the project directory. Then run this Executor.java. After ten minutes to hours (depending on the value of factors), it will generate files in the project directory. 

Please use WEKA CLI to convert it to arff file "java weka.core.converters.CSVLoader csv_directory > target_directory".
Then you have the arff file and can run it in WEKA.

To re-run this project, please delelte all the output files in project directory (the csv file and all text files except the celebrities_profiles.txt) and reset the value of factors.
