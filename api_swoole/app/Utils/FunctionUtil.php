<?php

namespace App\Utils;

use Library\Core\Env;
use CURLFile;
use DateTime;


class FunctionUtil
{
    /**
     * json_encode设置
     * @param array| object $obj
     * @return false|string
     */
    public static function jsonEncode(array|object $obj)
    {
        return json_encode($obj, JSON_UNESCAPED_SLASHES | JSON_UNESCAPED_UNICODE);
    }

    /**
     * post请求，并上传文件
     *
     *
     * @param [type] $url
     * @param [type] $data
     * @param [type] $file
     * @return 请求结果
     */
    public static function curlPostFile($url, $data, $file='')
    {
        if(!empty($file))
        {
            $data['media'] = new CURLFile($file);
        }
        $curl = curl_init();
        curl_setopt($curl, CURLOPT_URL, $url);
        curl_setopt($curl, CURLOPT_SSL_VERIFYPEER, FALSE);
        curl_setopt($curl, CURLOPT_SSL_VERIFYHOST,FALSE);
        curl_setopt($curl, CURLOPT_POST, 1);
        curl_setopt($curl, CURLOPT_POSTFIELDS, $data);
        curl_setopt($curl, CURLOPT_RETURNTRANSFER, 1);
        $result = curl_exec($curl);
        //判断有无出错
        if (curl_errno($curl) > 0)
        {
            echo curl_error($curl);
            $result = 'http请求出错！'.'['.curl_error($curl).']';
        }
        curl_close($curl);
        return $result;
    }

    /**
     * 传入数组进行HTTP POST请求
     *
     * @param string $url url地址
     * @param array $postData 请求数据
     * @param int $timeout 超时时间
     * @param array $header 请求头
     * @param array $dataType 请求数据类型。json、array
     */
    public static function curlPost(string $url, array $postData = array(), $timeout = 5, $header = "", $dataType = "")
    {
        $header = empty($header) ? '' : $header;
        //支持json数据数据提交
        if ($dataType == 'json') {
            $postString = json_encode($postData);
        } elseif ($dataType == 'array') {
            $postString = $postData;
        } elseif (is_array($postData)) {
            $postString = http_build_query($postData, '', '&');
        }

        $curl = curl_init(); // 启动一个CURL会话
        curl_setopt($curl, CURLOPT_URL, $url); // 要访问的地址
        curl_setopt($curl, CURLOPT_SSL_VERIFYPEER, false); // 对认证证书来源的检查   // https请求 不验证证书和hosts
        curl_setopt($curl, CURLOPT_SSL_VERIFYHOST, false); // 从证书中检查SSL加密算法是否存在
        //curl_setopt($curl, CURLOPT_FOLLOWLOCATION, 1); // 使用自动跳转
        //curl_setopt($curl, CURLOPT_AUTOREFERER, 1); // 自动设置Referer
        curl_setopt($curl, CURLOPT_POST, true); // 发送一个常规的Post请求
        curl_setopt($curl, CURLOPT_POSTFIELDS, $postString); // Post提交的数据包
        curl_setopt($curl, CURLOPT_CONNECTTIMEOUT, $timeout); // 设置超时限制防止死循环
        curl_setopt($curl, CURLOPT_TIMEOUT, $timeout);
        curl_setopt($curl, CURLOPT_USERAGENT, 'php curl');
        // 超时设置，以毫秒为单位
        curl_setopt($curl, CURLOPT_TIMEOUT_MS, $timeout * 1000);
        //curl_setopt($curl, CURLOPT_HEADER, 0); // 显示返回的Header区域内容
        curl_setopt($curl, CURLOPT_RETURNTRANSFER, true); // 获取的信息以文件流的形式返回

        // 设置请求头
        if ($header) {
            curl_setopt($curl, CURLOPT_HTTPHEADER, $header);
        }

        $result = curl_exec($curl);
        if (curl_errno($curl)) {
            $result = curl_error($curl);
        }
        // 打印请求的header信息
        //$a = curl_getinfo($ch);
        //var_dump($a);

        curl_close($curl);
        return $result;
    }

    /**
     * 传入数组进行HTTP GET请求
     *
     * @param string $url url地址
     * @param array $postData 请求数据
     * @param int $timeout 超时时间，以秒为单位
     * @param array $header 请求头
     * @param array $dataType 请求数据类型。json、array
     */
    public static function curlGet(string $url, array $getData = array(), $timeout = 5, $header = "", $dataType = "")
    {
        $header = empty($header) ? '' : $header;
        //支持json数据数据提交
        if ($dataType == 'json') {
            $getString = json_encode($getData);
        } elseif ($dataType == 'array') {
            $getString = $getData;
        } elseif (is_array($getData)) {
            $getString = http_build_query($getData, '', '&');
        }
        if ($getString) {
            $url = \stripos($url, '?') ? $url . '&' . $getString : $url . '?' . $getString;
        }

        $curl = curl_init();
        //设置抓取的url
        curl_setopt($curl, CURLOPT_URL, $url);
        //设置头文件的信息作为数据流输出
        curl_setopt($curl, CURLOPT_HEADER, 0);
        curl_setopt($curl, CURLOPT_CONNECTTIMEOUT, $timeout); // 设置超时限制防止死循环
        // 超时设置,以秒为单位
        curl_setopt($curl, CURLOPT_TIMEOUT, 1);
        // 超时设置，以毫秒为单位
        curl_setopt($curl, CURLOPT_TIMEOUT_MS, $timeout * 1000);

        // 设置请求头
        if ($header) {
            curl_setopt($curl, CURLOPT_HTTPHEADER, $header);
        }

        curl_setopt($curl, CURLOPT_USERAGENT, 'php curl');

        //设置获取的信息以文件流的形式返回，而不是直接输出。
        curl_setopt($curl, CURLOPT_RETURNTRANSFER, 1);
        curl_setopt($curl, CURLOPT_SSL_VERIFYPEER, false);
        curl_setopt($curl, CURLOPT_SSL_VERIFYHOST, false);
        //执行命令
        $result = curl_exec($curl);

        if (curl_errno($curl)) {
            $result = curl_error($curl);
        }
        curl_close($curl);
        return $result;
    }

    /**
     * 生成指定长度随机字符串
     *
     * @param int $len 字符串长度
     *
     * @return 生成的随机字符串
     */
    public static function randomStr(int $len = 6)
    {
        $chars = 'abcdefghjklmnpqrstuvwxyzABCDEFGHJKLMNPQRSTUVWXYZ23456789';
        $str = '';
        for ($i = 0; $i < $len; $i++) {
            $str .= $chars[mt_rand(0, strlen($chars) - 1)];
        }
        return $str;
    }

    /**
     * xml转数组
     */
    public static function xmlToArray(string $xml)
    {
        //禁止引用外部xml实体
        // libxml_disable_entity_loader(true);
        $xmlstring = simplexml_load_string($xml, 'SimpleXMLElement', LIBXML_NOCDATA);
        $val = json_decode(json_encode($xmlstring), true);
        return $val;
    }

    /**
     * 数组转树形结构
     */
    public static function arrayToTree(array $elements)
    {
        $addTree = function($treeList, $menu) use (&$addTree){
            if ($treeList) {
                foreach($treeList as $key => $value) {
                    if ($value['id'] == $menu['parentId']) {
                        $treeList[$key]['children'][] = $menu;
                    } else if (isset($value['children']) && count($value['children']) > 0){ // 判断是否有子节点
                        $treeList[$key]['children'] = $addTree($value['children'], $menu);
                    }
                }    
            }
            return $treeList;
        };
        $isExist = function($treeList, $menu) use(&$isExist) {
            $rst = false;
            foreach ($treeList as $value) {
                if ($value['id'] == $menu['id']) {
                    $rst = true;
                    break;
                } else if (isset($value['children']) && count($value['children']) > 0) {
                    $rst = $isExist($value['children'], $menu);
                }
    
                if ($rst) {
                    break;
                }
            }
            return $rst;
        };
        $alreadyIds = [];
        $treeList = [];
        if ($elements) {
            foreach($elements as $key=>$value) {
                if (in_array($value['id'], $alreadyIds)) {
                    continue;
                } else {
                    $alreadyIds[] = $value['id'];
                }
                // 将菜单添加到节点树
                $treeList = $addTree($treeList, $value);
                if (!$isExist($treeList, $value)) {
                    $treeList[] = $value;
                }
            }
        }
        return $treeList;
    }

    /**
     * 判断日期时间格式是否正确
     */
    public static function validateDate($date, $format = 'Y-m-d')
    {
        $d = DateTime::createFromFormat($format, $date);
        return $d && $d->format($format) === $date;
    }

    /**
     * Undocumented function
     *
     * @param [type] $file 上传的文件
     * @param string $toDir // 要移动到的路径
     * @param string $toFileName // 移动后的文件名
     * @return array 移动后的文件路径+文件名。位于位于storage/upload路径中。
     */
    public static function moveUploadFile($file, $toDir = 'tmp', $toFileName = '')
    {
        if (!isset($file['name']) || !$file['tmp_name']) {
            return ['code' => 1, 'message' => 'file对象参数错误'];
        }
        if (!file_exists($file['tmp_name'])) {
            return ['code' => 1, 'message' => 'file对象文件不存在'];
        }
        $exts = explode('.', $file['name']);
        $ext = $exts[count($exts) - 1]; // 文件后缀
        // 文件名称
        if ($toFileName) {
            $fileName = $toFileName . '.' . strtolower($ext);
        } else {
            $fileName = (microtime(true) * 10000) . '.' . strtolower($ext);
        }
        if ($toDir) {
            $uploadPath = 'upload' . DS . $toDir . DS; // 上传文件在项目存储文件夹中的相对路径
        } else {
            $uploadPath = 'upload' . DS . 'tmp' . DS; // 上传文件在项目存储文件夹中的相对路径
        }

        $uploadFullPath = ROOT_PATH . DS . 'storage' . DS . $uploadPath . DS; // 上传文件在羡慕存储文件夹中的完整路径
        if (!file_exists($uploadFullPath)) {
            mkdir($uploadFullPath, 0777, true);
        }
        $res = move_uploaded_file($file['tmp_name'], $uploadFullPath . $fileName); //函数将上传的文件移动到新位置。
        if ($res) {
            return ['code' => 0, 'fullFile' => $uploadFullPath . $fileName, 'fullPath' => DS . $uploadPath . $fileName, 'filePath' => DS . $uploadPath, 'fileName' => $fileName, 'ext' => $ext];
        } else {
            return ['code' => 1, 'message' => '文件移动失败'];
        }
    }

    /**
     * 将upload中的指定文件移动位置，可重命名
     *
     * @param [type] $originalFile 源文件
     * @param [type] $toDir 目标路径。位于storage/upload路径中
     * @param string $toFileName 新文件名
     * @return array
     */
    public static function renameUploadFile($originalFile, $toDir, $newFileName = '')
    {
        if (!\file_exists(ROOT_PATH . DS . 'storage' . DS . $originalFile)) {
            return ['code' => 1, 'message' => '源文件不存在'];
        }

        if ($toDir) {
            $newFilePath = 'upload' . DS . $toDir . DS;
        } else {
            return ['code' => 1, 'message' => '目标路径不存在'];
        }

        $exts = explode('.', $originalFile);
        if (count($exts) <= 1) {
            return ['code' => 1, 'message' => '源文件中不存在文件后缀'.$originalFile];
        }

        $ext = $exts[count($exts) - 1]; // 文件后缀
        if ($newFileName) {
            $newFileName = $newFileName . '.' . strtolower($ext) ;
        } else {
            $filePathArr = explode('/', $originalFile);
            $newFileName = $filePathArr[count($filePathArr) - 1]; // 文件后缀;
        }
        $newFileFullPath = ROOT_PATH . DS . 'storage' . DS . $newFilePath . DS;
        if (!file_exists($newFileFullPath)) {
            mkdir($newFileFullPath, 0777, true);
        }
        $res = rename(ROOT_PATH . DS . 'storage' . DS . $originalFile, $newFileFullPath . $newFileName);

        if ($res) {
            return ['code' => 0, 'fullFile' => $newFileFullPath . $newFileName, 'fullPath' => DS . $newFilePath . $newFileName, 'filePath' => DS . $newFilePath, 'fileName' => $newFileName, 'ext' => $ext];
        } else {
            return ['code' => 1, 'message' => '重命名失败'];
        }
    }


    /**
     * 处理头像
     *
     * @param string $img 图片相对地址。在storage中
     * @param int $type 1-头像 2-普通图片
     * @return 处理后的头像
     */
    public static function handleImg($img, $type = 2){
        // 判断是否是url
        if (filter_var($img, FILTER_VALIDATE_URL)) {
            return $img;
        } 
        if (!$img) {
            if ($type == 1) {
                return 'https://foruda.gitee.com/avatar/1677022544584087390/4835367_jmysy_1578975358.png';
            } else {
                return '';
            }
        } else {
            return self::handleUrl($img) ;
        }
    }

    /**
     * 处理url
     */
    public static function handleUrl($url){
        if (filter_var($url, FILTER_VALIDATE_URL)) {
            return $url;
        } 

        // 判断是否是以 / 开头
        if (substr($url, 0, 1) == '/') {
            $url = Env::get('API_URL') . $url;
        } else {
            $url = Env::get('API_URL') . DS . $url;
        }
            

        return $url;
    }

    /**
     * 数组转换为查询字符串
     */
    public static function arrayToQueryString($array, $prefix = '') {
        $query = [];
        foreach ($array as $key => $value) {
            $key = $prefix ? "{$prefix}[{$key}]" : $key;
            if (is_array($value)) {
                $query[] = self::arrayToQueryString($value, $key);
            } else {
                $query[] = urlencode($key) . '=' . urlencode($value);
            }
        }
        return implode('&', $query);
    }

    private static $s3Client;

    /**
     * 上传文件到S3存储
     * @param string $localFilePath 本地文件路径
     * @param string $contentType 文件内容类型
     * @param string $s3ObjectKey S3对象键
     * @return array 包含上传结果的数组
     */
    public static function uploadFileToS3($localFilePath, $contentType = '', $s3ObjectKey = '') {
        try {
            // 引入S3Client类
            if (class_exists('Aws\S3\S3Client')) {
                $s3ClientClass = 'Aws\S3\S3Client';
            } else {
                // 尝试其他可能的S3Client类路径
                $possibleClasses = [
                    'League\Flysystem\AwsS3v3\AwsS3Adapter',
                    'Aws\S3\S3Client',
                    'S3Client'
                ];
                $s3ClientClass = null;
                foreach ($possibleClasses as $className) {
                    if (class_exists($className)) {
                        $s3ClientClass = $className;
                        break;
                    }
                }
                if (!$s3ClientClass) {
                    throw new \Exception('S3Client class not found');
                }
            }

            if(self::$s3Client == null) {
                self::$s3Client = new $s3ClientClass([
                    'version' => 'latest',
                    'region'  => Env::get('FILE_S3_REGION'),
                    'endpoint' => Env::get('FILE_S3_ENDPOINT'),
                    'use_path_style_endpoint' => true,
                    'credentials' => [
                        'key' => Env::get('FILE_S3_ACCESS_KEY'),
                        'secret' => Env::get('FILE_S3_SECRET_KEY'),
                    ]
                ]); 
            }

            $bucketName = Env::get('FILE_S3_BUCKET'); // 替换为你的存储桶名称

            $body = file_get_contents($localFilePath);
            // 执行上传
            $res = self::$s3Client->putObject([
                'Bucket'     => $bucketName,
                'Key'        => $s3ObjectKey,
                'Body'       => $body, // 以二进制模式读取本地文件
                'ContentType' => $contentType,
            ]);
            
            // 标准化返回结果格式
            $result = [];
            if (is_object($res)) {
                // 如果是对象，转换为数组
                $resArray = json_decode(json_encode($res), true);
                $result['ObjectURL'] = $resArray['ObjectURL'] ?? '';
                $result['Key'] = $resArray['Key'] ?? '';
                $result['Bucket'] = $resArray['Bucket'] ?? '';
            } elseif (is_array($res)) {
                // 如果是数组，直接使用
                $result['ObjectURL'] = $res['ObjectURL'] ?? '';
                $result['Key'] = $res['Key'] ?? '';
                $result['Bucket'] = $res['Bucket'] ?? '';
            } else {
                // 其他情况，尝试构建结果
                $result['ObjectURL'] = '';
            }
            
            // 如果没有ObjectURL，尝试构建
            if (empty($result['ObjectURL'])) {
                $endpoint = rtrim(Env::get('FILE_S3_ENDPOINT'), '/');
                $result['ObjectURL'] = "{$endpoint}/{$bucketName}/{$s3ObjectKey}";
            }
            
            return $result;
        } catch (\Exception $e) {
            // 记录错误日志
            error_log('S3文件上传失败: ' . $e->getMessage());
            throw new \Exception('S3文件上传失败: ' . $e->getMessage());
        }
    }
}
