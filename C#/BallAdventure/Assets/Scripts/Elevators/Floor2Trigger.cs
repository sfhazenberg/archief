using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Floor2Trigger : MonoBehaviour {

    public ElevWallW ElevWallW;
    public Floor2Door Floor2Door;

    private void OnCollisionEnter(Collision collision) {
        if (collision.gameObject.CompareTag("Player")) {
            print("Floor2Trigger active");
            Floor2Door.LowerDoor();
            ElevWallW.LowerDoor();
        }
    }

    private void OnCollisionExit(Collision collision) {
        if (collision.gameObject.CompareTag("Player")) {
            Floor2Door.RaiseDoor();
            ElevWallW.RaiseDoor();
        }
    }
}
