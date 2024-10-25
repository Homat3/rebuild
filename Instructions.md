# BuildTheWorldForNPU
Made by Homat3

## 公约
- 注册新东西以及存放新资源文件时，要条理清晰，把代码写到该写的地方，把东西放到该放的位置上
- 日志能加最好都加上
- 保持代码美观

## 重构结构简述
- npu
    - blocks  
        - npublocknewclasses              新方块的母体
        - NpuBlocks.class                 一切新方块应该在此注册
    - creativemodtab
        - CreativeModeTab.class           用于向原版创造模式物品栏添加新物品
        - NpuCreativeModeTabs.class       一切新创造模式物品栏应该在此注册
    - entities
        - npuentitynewclasses
            - npuentitities               新实体类，实体最好一个实体一个类，都放在这里
            - npuentitityrenderers        新实体对应的渲染方式类，一一对应，都放在这里
        - NpuEntities.class               一切新实体应该在此注册
        - NpuEntitySubscriber.class       向事件加入新实体与新渲染方式的链接
    - items
        - npuentitynewclasses             新物品的母体（照目前看貌似不太需要）
        - NpuItems.class                  一切模组物品（包括方块物品）应该在此注册
    - util                                一些工具，一般不用改它
        - Logger.class                    用于输出日志
        - Reference.class                 用于获得模组基本信息
        - Register.class                  用于提供新东西的注册器
    - Config.class                        别改就是
    - NPU                                 主类（一般不动它）

## 如何操作
大部分操作都可套用示例完成，不要乱删注释

### 注册新方块
注意涉及到的不同的字段命名EXAMPLE,EXAMPLE_ID,EXAMPLE_ITEM,EXAMPLE_ITEM_ID  
注意不要修改原模组中的方块ID，不然移植后Minecraft认不得

1. 根据原模组中方块的信息判断是哪种方块（Normal/HorizontalDirectional后续会加入更多类型的模板）
2. 在NpuBlocks.class中相应位置使用对应的类注册该方块（声明方块字段以及对应方块ID字段），这里注意有些方块的碰撞体积需要修改，原模组不知道为啥有些问题
3. 在NpuItems.class中相应位置注册对应物品（声明物品字段以及对应物品ID字段）
4. 在CreativeModeTab或者NpuCreativeModeTab中注册到物品栏里（可选，因为此时已经可以用指令获得了）
5. 从原模组资源文件中导入资源，导入前记得搜索一下重构模组这里是不是已经有了，而且需要对导入的json文件中的一些路径进行修改

涉及资源lang，blockstates，models/blocks，models/item，textures/block

NpuBlocks.class里声明了常用材料属性和常用体积模型属性的枚举，有需求可以自己加

### 注册新物品
注意涉及到的不同的字段命名EXAMPLE,EXAMPLE_ID

1. 在NpuItems.class中相应位置注册物品（声明物品字段以及对应物品ID字段）
2. 在CreativeModeTab或者NpuCreativeModeTab中注册到物品栏里（可选，因为此时已经可以用指令获得了）
3. 从原模组资源文件中导入资源，导入前记得搜索一下重构模组这里是不是已经有了，而且需要对导入的json文件中的一些路径进行修改

涉及资源lang，models/item，textures/item

### 注册新实体
注意涉及到的不同的字段命名EXAMPLE,EXAMPLE_ID

1. 在npuentitynewclasses/npuentities下编写新实体类，参照原模组代码慢慢翻译成新API即可（这里注意写一个registerAttributes方法来返回实体属性）
2. 在NpuEntities.class中相应位置使用对应的类注册该实体（声明实体字段以及对应实体ID字段）
3. 在npuentitynewclasses/npuentityrenderers下编写新实体渲染类，参照原模组代码慢慢翻译成新API即可（这里需要自行指定贴图的位置，所以注意条理性，别乱放贴图）
4. 在NpuEntitySubscriber.class中相应位置使用1.中的registerAttributes方法链接渲染类和实体类
5. 在在NpuItems.class中相应位置注册生物蛋物品（声明物品字段以及对应物品ID字段）（可选，因为此时已经可以用指令召唤出来了）
6. 从原模组资源文件中导入资源，导入前记得搜索一下重构模组这里是不是已经有了，而且需要对导入的json文件中的一些路径进行修改

涉及资源lang，textures/entity

### 注册新实体方块
目前貌似没用需求

### 注册新创造模式物品栏
注意涉及到的不同的字段命名EXAMPLE_TAB,EXAMPLE_TAB_ID

1. 在NpuCreativeModeTab中相应位置注册（声明物品栏字段以及对应物品栏ID字段）

## 后记
-有新的模板或API的需求或者已有的模板或API有更优化的方案可以告诉我