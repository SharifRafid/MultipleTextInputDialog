# MultipleTextInputDialog
 An Android library for getting multiple inputs from user at once
 
 
 Example Screenshot : 

![image](https://user-images.githubusercontent.com/49372990/179830193-a204c391-6d90-440d-b701-830a61356519.png)

**Implement Library** 

The library is available on JitPack, for implementing, just go to your project-level
or root level build.gradle file and add the below code if not added already :

```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' } // Add This Line
		}
	}
```
 
 Then go to your app level build.gradle file and add this line : 
```
dependencies {
	  implementation 'com.github.SharifRafid:MultipleTextInputDialog:1.0.0' //Add this line in the dependencies
	}
```

**Usage Of Library**

Java : 
```
new TextInputDialog(
                context,
                labels,
                "",
                "",
                false,
                new OnSubmitListener() {
                    @Override
                    public void onCancelled() {

                    }

                    @Override
                    public void onSubmit(@NonNull ArrayList<String> values) {

                    }
                }
        ).show();
```
Kotlin : 
```
 TextInputDialog(
            this,
            arrayListOf("Label 1","Label 2","Label 3","Label 4"),
            "Test Dialog Title",
            "Submit",
            false,
            object : OnSubmitListener{
                override fun onCancelled() {
                    Toast.makeText(this@MainActivity, "Cancelled", Toast.LENGTH_SHORT).show()
                }

                override fun onSubmit(values: ArrayList<String>) {
                    values.map {
                        Toast.makeText(this@MainActivity, it, Toast.LENGTH_SHORT).show()
                    }
                }

            }
        ).show()
```
