![moko-units](img/logo.png)  
[![GitHub license](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](http://www.apache.org/licenses/LICENSE-2.0) [![Download](https://api.bintray.com/packages/icerockdev/moko/moko-units/images/download.svg) ](https://bintray.com/icerockdev/moko/moko-units/_latestVersion)

# Mobile Kotlin units
This is a Kotlin MultiPlatform library that provides RecyclerView/UITableView/UICollectionView filling from common code.

## Table of Contents
- [Features](#features)
- [Requirements](#requirements)
- [Versions](#versions)
- [Installation](#installation)
- [Usage](#usage)
- [Samples](#samples)
- [Set Up Locally](#setup-locally)
- [Contributing](#contributing)
- [License](#license)

## Features
- **** .

Для Android `BinderAdapter` это `RecyclerView.Adapter` не требующий наследования и написания специфичного
 кода для отображения новых типов элементов. Плагин `binderadapter-plugin` генерирует нужные для работы
 `BinderAdapter` классы автоматически, из файлов лейаутов с `DataBinding`.

Для iOS используется только абстракция `BindingClass` - это класс элемента списка. Используется `BindingClass`
 в связке с `FlatUnitTableViewDataSource` или `FlatUnitCollectionViewDataSource`.

Общий адаптер, который работает через DataBinding. Должен ускорить процесс написания кода (Теперь не надо каждый раз писать адаптер)

# Integration
- Интегрируйте пакеты binderadapter(Включая внутренние пакеты) через скрипт integration-master.sh
  
# Usage

- Сбилдите проект (**Build -> Make Project**)
- После получения данных создайте список из сгенерированных файлов. **Пример** :

  ```
   List<BindingClass> models = new ArrayList<>();
   models.add(new ItemOne().setUnit(new ModelOne("1")));
   models.add(new ItemTwo().setUnit(new ModelTwo("a", "21")).setClicker(this));
   models.add(new ItemTwo().setUnit(new ModelTwo("b", "22")).setClicker(this));
   BinderAdapter adapter = new BinderAdapter();
   recyclerView.setAdapter(adapter);
   adapter.setList(models);
  ```
  
  
  Где `ItemOne` и `ItemTwo` сгенерированные модели по xml'кам `item_one.xml` , и `item_two.xml` , а `ModelOne`, и `ModelTwo` это мои модели(которые например приходят с сервера), а методы `setUnit` и `setClicker` это обычные сеттеры Variable'ов для вашего элемента.

**Так же** у Сгенерированных классов есть метод `setItemId(long itemId)` ,предназначенный для правильной анимации адаптера(Адаптер использует Stable Id's), Дефолтное значение - HashCode вашего объекта.

Если хотите посмотреть полный пример, смотрите в [Demo project](https://gitlab.icerockdev.com/scl/scl-android-samples/binder-adapter)


## Requirements
- Gradle version 5.4.1+
- Android API 21+
- iOS version 9.0+

## Versions
- kotlin 1.3.50
  - 0.1.0

## Installation
### Plugin
add to `settings.gradle`: 
``` 
includeBuild "scl/binderadapter/binderadapter-plugin" 
```
add in root `build.gradle`:
```
buildscript {
    dependencies {
        classpath module("com.icerockdev:binderadapter-plugin:0.1.0")
    }
}
``` 
add in `build.gradle` of project where layout files must be processed:
```
apply plugin: 'binderadapter-plugin'

android {
    sourceSets.main.java.srcDirs += project.buildDir.path + "/generated/source/binderadapter/src/main/java"
}

binderAdapter {
    classesPackage "<package_for_binder_adapter_generated_classes>"
    dataBindingPackage "<package_of_databinding_generated_classes>"
}

dependencies {
    implementation project(':scl:binderadapter')
}
```

### Runtime
## Android
К проекту подключаем модуль `binderadapter` (в бойлерплейте сразу настроено подключение через `mpp-library/projects.gradle`);  
Для генерации из лейаутов готовых классов биндинга подключаем плагин:  
* В `settings.gradle`: `includeBuild("mpp-library/scl/binderadapter/binderadapter-plugin")`
* В проекте, где лежат лейауты, из которых нужно генерировать классы:
```groovy
plugins {
    id 'binderadapter-plugin'
}

android {
    sourceSets.main.java.srcDirs += project.buildDir.path + "/generated/source/binderadapter/src/main/java"
}

binderAdapter {
    classesPackage "<пакет для новых сгенерированных классов айтемов>"
    dataBindingPackage "<пакет куда генерирует DataBinding классы свои (соответсвует appId)>"
}
```

## iOS
В котлине подключаем модуль `binderAdapter`;
А дальше все в нативной части в свифте:
* Подключаем к проекту содержимое `mpp-library/binderadapter/src/iosMain/swift`;
* Подключаем содержимое `ios-app/scl/ui/UnitTableViewDataSource` (для таблиц) и/или `ios-app/scl/ui/UnitCollectionViewDataSource` (для коллекций). 

root build.gradle  
```groovy
buildscript {
    repositories {
        maven { url = "https://dl.bintray.com/icerockdev/plugins" }
    }

    dependencies {
        classpath "dev.icerock.moko:resources-generator:0.3.0"
    }
}


allprojects {
    repositories {
        maven { url = "https://dl.bintray.com/icerockdev/moko" }
    }
}
```

project build.gradle
```groovy
apply plugin: "dev.icerock.mobile.multiplatform-resources"

dependencies {
    commonMainApi("dev.icerock.moko:resources:0.3.0")
}
```

settings.gradle  
```groovy
enableFeaturePreview("GRADLE_METADATA")
```

On iOS, in addition to the Kotlin library add Pod in the Podfile.
```ruby
pod 'MultiPlatformLibraryResources', :git => 'https://github.com/icerockdev/moko-resources.git', :tag => 'release/0.2.0'
```
**`MultiPlatformLibraryResources` CocoaPod requires that the framework compiled from Kotlin be named 
`MultiPlatformLibrary` and be connected as a CocoaPod `MultiPlatformLibrary`. 
[Here](sample/ios-app/Podfile)'s an example.
To simplify integration with MultiPlatformFramework you can use [mobile-multiplatform-plugin](https://github.com/icerockdev/mobile-multiplatform-gradle-plugin)**.  
`MultiPlatformLibraryResources` CocoaPod contains an extension `localized` for `StringDesc`.

## Usage
common:
```kotlin
interface UnitFactory {
    fun createHeader(text: String): BindingClass
    fun createProfileTile(profileId: Long, avatarUrl: String, username: String): BindingClass
}

class ViewModel(unitFactory: UnitFactory) {
    val items = listOf(
        unitFactory.createHeader("Programmers"),
        unitFactory.createProfileTile(1, "url", "Mikhailov"),
        unitFactory.createProfileTile(2, "url", "Babenko"),
        unitFactory.createProfileTile(3, "url", "Tchernov"),
        unitFactory.createHeader("Designers"),
        unitFactory.createProfileTile(4, "url", "Eugeny")
    )
}
```

android:
```xml
<androidx.recyclerview.widget.RecyclerView
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            app:items="@{viewModel.items}"
            app:adapter="@{`com.icerockdev.mpp.binderadapter.adapter.BinderAdapter`}"
            app:layoutManager="androidx.recyclerview.widget.LinearLayoutManager"/>
```
```kotlin
object UnitFactoryImpl: UnitFactory {
    fun createHeader(text: String): BindingClass {
        return LayoutHeader()
            .setText(text)
            .setItemId(text.hashCode())
    }
    
    fun createProfileTile(profileId: Long, avatarUrl: String, username: String): BindingClass {
        return LayoutProfileTile()
            .setAvatarUrl(avatarUrl)
            .setUserName(username)
            .setItemId(profileId)
    }
}
```
```kotlin
mBinding.viewModel = ViewModel(UnitFactoryImpl)
```

iOS:
```swift
class UnitFactoryImpl: NSObject, UnitFactory {

  func createHeader(text: String) -> BindingClass {
    let data = HeaderTableViewCell.CellModel(text: text)
    return UIBindingTableViewCellUnit<HeaderTableViewCell>(
      data: data,
      reusable: R.nib.headerTableViewCell,
      configurator: nil,
      height: 48)
  }
  
  func createProfileTile(profileId: Long, avatarUrl: String, username: String): BindingClass {
    let data = ProfileTableViewCell.CellModel(avatarUrl: avatarUrl, username: username)
    return UIBindingTableViewCellUnit<ProfileTableViewCell>(
      data: data,
      reusable: R.nib.profileTableViewCell,
      configurator: nil,
      height: 56)
  }
}
```
```swift
let viewModel = ViewModel(unitFactory: UnitFactoryImpl())
let tableDataSource = FlatUnitTableViewDataSource()

tableView.register(reusable: R.nib.headerTableViewCell)
tableView.register(reusable: R.nib.profileTableViewCell)
unitTableViewDelegate = tableDataSource.setup(for: tableView)

tableDataSource.units = viewModel.items
tableView.reloadTable()
```

## Samples
Please see more examples in the [sample directory](sample).

## Set Up Locally 
- The [resources directory](resources) contains the `resources` library;
- The [gradle-plugin directory](gradle-plugin) contains a gradle plugin with a `MR` class generator;
- The [sample directory](sample) contains sample apps for Android and iOS; plus the mpp-library connected to the apps;
- For local testing a library use the `:resources:publishToMavenLocal` gradle task - so that sample apps use the locally published version.
- For local testing a plugin use the `:gradle-plugin:publishToMavenLocal` gradle task so that sample apps will use the locally published version.

## Contributing
All development (both new features and bug fixes) is performed in the `develop` branch. This way `master` always contains the sources of the most recently released version. Please send PRs with bug fixes to the `develop` branch. Documentation fixes in the markdown files are an exception to this rule. They are updated directly in `master`.

The `develop` branch is pushed to `master` on release.

For more details on contributing please see the [contributing guide](CONTRIBUTING.md).

## License
        
    Copyright 2019 IceRock MAG Inc.
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
       http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.