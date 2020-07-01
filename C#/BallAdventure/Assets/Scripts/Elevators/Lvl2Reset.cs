using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Lvl2Reset : MonoBehaviour {

    public ElevWallW ElevWallW;
    public Floor2Trigger Floor2Trigger;

    private void OnCollisionEnter(Collision collision) {
        if(collision.gameObject.CompareTag("Player")) {
            print("Level 2 reset");
            ElevWallW.RaiseDoor();
        }
    }
}
