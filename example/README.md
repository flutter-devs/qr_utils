# Example

```dart

import 'dart:typed_data';

import 'package:flutter/material.dart';
import 'dart:async';
import 'dart:convert';

import 'package:flutter/services.dart';
import 'package:qr_utils/qr_utils.dart';

void main() => runApp(MyApp());

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  String _content = 'Undefined';
  String _qrBase64Content = 'Undefined';
  Image _qrImg;

  TextEditingController _qrTextEditingController = TextEditingController();

  String _error;

  GlobalKey<ScaffoldState> _scaffoldKey = GlobalKey();

  @override
  void initState() {
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        key: _scaffoldKey,
        appBar: AppBar(
          title: const Text('QR Scanner Plugin'),
        ),
        body: Container(
          padding: EdgeInsets.all(8.0),
          child: SingleChildScrollView(
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: <Widget>[
                Text(
                  "Scan QR: ",
                  style: TextStyle(
                    fontSize: 24.0,
                  ),
                ),
                Text(_content),
                SizedBox(
                  height: 24.0,
                ),
                FlatButton(
                  color: Colors.blue,
                  onPressed: _scanQR,
                  child: Text(
                    'Scan QR',
                    style: TextStyle(color: Colors.white),
                  ),
                ),
                SizedBox(
                  height: 24.0,
                ),
                Divider(),
                SizedBox(
                  height: 24.0,
                ),
                Text(
                  "Generate QR: ",
                  style: TextStyle(
                    fontSize: 24.0,
                  ),
                ),
                SizedBox(
                  height: 12.0,
                ),
                TextFormField(
                  controller: _qrTextEditingController,
                  decoration: InputDecoration(
                      hintText: 'QR Content',
                      labelText: 'QR Content',
                      border: OutlineInputBorder()),
                ),
                SizedBox(
                  height: 12.0,
                ),
                _qrImg != null
                    ? Container(
                        child: _qrImg,
                        width: 120.0,
                        height: 120.0,
                      )
                    : Image.asset(
                        'assets/images/ic_no_image.png',
                        width: 120.0,
                        height: 120.0,
                        fit: BoxFit.cover,
                      ),
                SizedBox(
                  height: 16.0,
                ),
                FlatButton(
                  color: Colors.blue,
                  onPressed: () => _generateQR(_qrTextEditingController.text),
                  child: Text(
                    'Generate QR',
                    style: TextStyle(color: Colors.white),
                  ),
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }

  void _scanQR() async {
    String result;
    try {
      result = await QrUtils.scanQR;
    } on PlatformException {
      result = 'Process Failed!';
    }

    setState(() {
      _content = result;
    });
  }

  void _generateQR(String content) async {
    if (content.trim().length == 0) {
      _scaffoldKey.currentState
          .showSnackBar(SnackBar(content: Text('Please enter qr content')));
      setState(() {
        _qrImg = null;
      });
      return;
    }
    Image image;
    try {
      image = await QrUtils.generateQR(content);
    } on PlatformException {
      image = null;
    }
    setState(() {
      _qrImg = image;
    });
  }
}


 ```